# integrant-tools

A library that makes integrant a little easier to use.

## Usage

For example, you can write the following in `dev.clj`:

```clojure
(ns dev
  (:require [clojure.tools.namespace.repl :as ns-repl]
            [integrant-tools.core :as ig-tools]))

(ns-repl/set-refresh-dirs "dev/src" "src")

(integrant.repl/set-prep! #(ig-tools/init "config.edn" "local.edn"))
```

## Features

### ref

You can use more simpler `#ref` instead of `#ig/ref`:

```clojure
{:adapter/jetty {:port 8080, :handler #ref :handler/greet}
 :handler/greet {:name "Alice"}}
```

### def

You can define a constant by `:def` such as `:duct/const`:

```clojure
{[:def :my-app/config] {:port 8080 :host "localhost"}}
```

### env

Just a `#duct/env` port:

```clojure
{:adapter/jetty {:port #env ["PORT" Int :or 8080], :handler ref :handler/greet}
 :handler/greet {:name "Alice"}}
```

# License

Copyright 2020-2021 [Xcoo, Inc.][xcoo]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[xcoo]: https://xcoo.com
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
