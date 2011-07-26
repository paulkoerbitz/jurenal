(ns jurenal.templates
  (:require [clj-soy.template :as soy]))

(def *posts* (soy/build "src/jurenal/templates/postlist.soy"))
