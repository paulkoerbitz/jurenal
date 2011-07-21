(ns jurenal.templates
  (:require [clj-soy.template :as soy]))

(def *hello* (soy/build "src/jurenal/templates/hello.soy"))
(def *posts* (soy/build "src/jurenal/templates/postlist.soy"))
