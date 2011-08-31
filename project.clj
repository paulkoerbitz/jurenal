(defproject jurenal "0.0.1"
  :description "Clojure blog for Google App Engine"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.google.appengine/appengine-tools-sdk "1.5.2"]
                 [ring/ring-servlet "0.3.8"]
                 [ring "0.3.8"]
                 [compojure "0.6.3"]
                 [closure-templates-clj "0.0.1"]]
  ;;:cljs-devmode
  ;;{:dir "/home/paul/hck/clj/jurenal/cljs"
  ;; :src-dir "/home/paul/hck/clj/jurenal/cljs/src"
  ;; :output-dir "/home/paul/hck/clj/jurenal/war/media/js/out"
  ;; :output-to "/home/paul/hck/clj/jurenal/war/media/js/jurenal.js" }
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     ;; [ring/ring-jetty-adapter "0.3.11"]
                     ;; [clj-http "0.1.3"]
                     [clojure-source "1.2.1"]
                     [appengine-magic "0.4.2-SNAPSHOT"]]
  ;;:jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"]
  )
