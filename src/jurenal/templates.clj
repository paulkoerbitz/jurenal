(ns jurenal.templates
  (:require [closure.templates.core :as tmpl]))

(tmpl/deftemplate listview [posts editable]
  {:postlist posts :editable editable})

(tmpl/deftemplate detailedview [post editable]
  {:post post :editable editable})

(tmpl/deftemplate editview [post]
  {:post post})
