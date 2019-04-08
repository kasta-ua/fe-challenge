(defproject fe-challenge "1.0"
  :description "Kasta FrontEnd challenge"
  :url "https://github.com/kasta-ua/fe-challenge"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :main kasta.core

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [aleph "0.4.6" :exclusions [manifold]]
                 [manifold "0.1.8"]
                 [mount "0.1.16"]
                 [nrepl "0.6.0"]

                 [org.clojure/clojurescript "1.10.520"
                  :exclusions [org.mozilla/rhino]]
                 [rum "0.11.3"]

                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.clojure/tools.logging "0.4.1"]]

  :profiles
  {:dev {:source-paths #{"dev"}
         :resource-paths #{"resources" "target"}
         :dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]
                        [cider/cider-nrepl "0.21.1"]
                        [hawk "0.2.11"]
                        [mrmcc3/libsass-clj "0.1.8"]
                        [figwheel-sidecar "0.5.18"
                         :exclusions [org.clojure/clojurescript
                                      org.apache.maven.wagon/wagon-http
                                      org.clojure/tools.nrepl]]]}})
