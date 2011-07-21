(ns jurenal.views
  (:require 
    [jurenal.models :as models]
    [jurenal.templates :as tpl]
    [ring.util.response :as response]
    [clj-soy.template :as soy])
  (:import play.templates.JavaExtensions))

(defn date->str [x] (if (= (type x) java.util.Date) (str x) x))
(defn long->int [x] (if (= (type x) java.lang.Long) (.intValue x) x))
(defn keyword->str [x] (apply str (replace {\- \_} (if (keyword? x) (name x) x))))

(defn map->soy [item]
  "Substitutes keywords in keys with strings and Longs in values with Integers.
  {:a 1 :b \"String\"} becomes {\"a\" 1 \"b\" \"String\"} and the type of
  the '1' will be Integer and not long (this is needed for soy to work)"
  (let [f (fn [[k v]] [(keyword->str k) (date->str (long->int v))])]
    (into {} (map f item))))

(defn index []
  (soy/render tpl/*posts* "jurenal.postlist"
    {:postlist (map map->soy (models/fetch-all))})) 

(defn create-post [] 
  (soy/render tpl/*posts* "jurenal.editpost"
    {:post {"title" "" "body" ""}}))

(defn show-post [slug] 
  (soy/render tpl/*posts* "jurenal.post" 
                {:post (map->soy (first (models/fetch slug)))}))

(defn edit-post [slug]
  (soy/render tpl/*posts* "jurenal.editpost"
    {:post (map->soy (first (models/fetch slug)) )}))

(defn update-post [{{slug "slug" title "title" body "body"} :params}]
  (println slug title body)
  (let [slug (if slug slug (JavaExtensions/slugify title))]
    (models/update {:slug slug :title title :body body})
    (response/redirect (str "/" slug))))
  
(defn delete-post [slug]
  (models/delete slug)
  (response/redirect "/"))