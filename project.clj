(defproject jurenal "0.0.1"
  :description "Clojure blog for Google App Engine"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.google.appengine/appengine-tools-sdk "1.5.2"]
                 [ring/ring-servlet "0.3.8"]
                 [ring "0.3.8"]
                 [clj-soy "0.1.0"]
                 [compojure "0.6.3"]
                 [closure-templates-clj "0.0.1"]]
  :dev-dependencies [[swank-clojure "1.3.1"]
                     [appengine-magic "0.4.2-SNAPSHOT"]])
