(defproject xcoo/integrant-tools "0.2.0"
  :description ""
  :url "https://github.com/xcoo/integrant-tools"
  :license {}
  :dependencies [[integrant "0.8.0" :exclusions [org.clojure/clojure]]]
  :source-paths ["src"]
  :resource-paths ["resources"]
  :repl-options {:init-ns integrant-tools.core}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]]
                   :global-vars {*warn-on-reflection* true}}
             :test {:resource-paths ["test-resources"]}
             :uberjar {:aot :all}}
  :jar-exclusions [#".+?\.config\.clj"]
  :signing {:gpg-key "developer@xcoo.jp"})
