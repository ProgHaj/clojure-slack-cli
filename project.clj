(defproject clojure-slack-cli "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-slack-client "0.1.4-SNAPSHOT"]
                 [stylefruits/gniazdo "0.4.1"]
                 [http-kit "2.1.18"]
                 [clj-http "2.1.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot clojure-slack-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
