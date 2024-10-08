(defproject hello-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;; https://mvnrepository.com/artifact/http-kit/http-kit
                 [http-kit/http-kit "2.8.0"]]
  :main hello-clojure.core
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns hello-clojure.core})
