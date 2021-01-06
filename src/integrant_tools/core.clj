(ns integrant-tools.core
  (:require [integrant.core :as ig]
            [clojure.java.io :as io]
            [clojure.walk :as walk]
            [integrant-tools.env :as env])
  (:import [java.io File]))

(defmethod ig/init-key ::def [_ v] v)

;;;

(def ^:private reader-tag
  {:readers {'ref ig/ref
             'env env/env}})

(def ^:private target-key
  {:def ::def})

;;;

(defn- edn?
  [^File file]
  (-> file
      .getName
      (.endsWith ".edn")))

(defn- edn-file?
  [^File file]
  (and (.isFile file)
       (edn? file)))

(defn get-config-filename
  ([path]
   (->> path
        io/file
        .listFiles
        (filter edn-file?)
        (mapv #(.getName ^File %))))
  ([]
   (get-config-filename "resources")))

;;;

(defn- load-config
  [config]
  (some-> config
          io/resource
          slurp))

(defn- sanitize-config
  [config]
  (->> config
       (ig/read-string reader-tag)
       (walk/postwalk-replace target-key)))

(defn- unify-configs
  [configs]
  (->> configs
       (keep load-config)
       (map sanitize-config)
       (apply merge-with into)))

(defn init
  [& configs]
  (-> configs
      unify-configs
      (doto ig/load-namespaces)))
