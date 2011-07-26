(ns jurenal.templates
  (:require [clj-soy.template :as soy]))

(def *posts* (soy/build "/home/paul/hck/clj/jurenal/war/WEB-INF/templates/postlist.soy"))
