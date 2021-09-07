# integrant-tools

A Clojure library designed to ... well, that part is up to you.

## Usage

### Setup

In order to use integrant-tools, you should change `dev.clj` like this:

```clojure
(ns dev
  (:require [clojure.tools.namespace.repl :as ns-repl]
            [integrant-tools.core :as ig-tools]))

(ns-repl/set-refresh-dirs "dev/src" "src")

(integrant.repl/set-prep! #(ig-tools/init "config.edn" "local.edn"))
```

### Abilities

#### ref

You can use `#ref` instead of `#ig/ref`.

Example:

```clojure
{:adapter/jetty {:port 8080, :handler #ref :handler/greet}
 :handler/greet {:name "Alice"}}
```

#### def

You can define variable by `[:def ]`. You don't need define any function.

```clojure
{[:def :foo/bar] "foo"}
```

#### env

Resolve an environment variable by name. Optionally accepts a type for
coercion, and a keyword option, `:or`, that provides a default value if the
environment variable is missing.

```clojure
{:adapter/jetty {:port #env ["PORT" Int :or 8080], :handler ref :handler/greet}
 :handler/greet {:name "Alice"}}
```
