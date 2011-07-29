(ns jurenal.templates
  (:require [closure.templates.core :as tmpl]))

(tmpl/deftemplate postlist [posts editable]
  {:postlist posts :editable editable})

(tmpl/deftemplate post [post editable]
  {:post post :editable editable})

(tmpl/deftemplate editpost [post]
  {:post post})

;(def *posts* (soy/build "static/postlist.soy"))

