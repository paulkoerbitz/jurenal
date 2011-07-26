(ns jurenal.views
  (:require 
    [jurenal.models :as models]
    [jurenal.templates :as tpl]
    [jurenal.utils :as utils]
    [ring.util.response :as response]
    [clj-soy.template :as soy]
    [appengine-magic.services.user :as usr]))

(defn date->str [x] (if (= (type x) java.util.Date) (str x) x))
(defn long->int [x] (if (= (type x) java.lang.Long) (.intValue x) x))
(defn keyword->str [x] (apply str (replace {\- \_} (if (keyword? x) (name x) x))))
(defn text->str [x] (if (= (type x) com.google.appengine.api.datastore.Text)
                      (.getValue x)
                      x))

(defn map->soy [item]
  "Substitutes keywords in keys with strings and Longs in values with Integers.
  {:a 1 :b \"String\"} becomes {\"a\" 1 \"b\" \"String\"} and the type of
  the '1' will be Integer and not long (this is needed for soy to work)"
  (let [f (fn [[k v]] [(keyword->str k) (-> v text->str date->str long->int)])]
    (into {} (map f item))))

(defn respond-404 []
  {:status 404 
   :headers {"Content-Type" "text/html"} 
   :body "<h1>Four-oh-four</h1>"})

(defn index []
  (soy/render tpl/*posts* "jurenal.postlist"
              {:postlist (map map->soy (models/fetch-all))
               :editable (utils/authorized?)}))

(defn create-post []
   (utils/check-auth
    (soy/render tpl/*posts* "jurenal.editpost"
                {:post {"title" "" "body" "" "slug" ""}})))

(defn show-post [slug]
  (let [post (models/fetch slug)]
    (if (nil? post)
      (respond-404)
      (soy/render tpl/*posts* "jurenal.post" 
              {:post (map->soy (models/fetch slug))
               :editable (utils/authorized?)}))))

(defn edit-post [slug]
   (utils/check-auth
    (soy/render tpl/*posts* "jurenal.editpost"
    {:post (map->soy (models/fetch slug))})))
  
(defn update-post [{{slug "slug" title "title" body "body"} :params}]
  (utils/check-auth
   (do
     (if (or (= slug "") (nil? slug))
       (let [slug (utils/slugify title)]
         (models/create {:slug slug :title title :body body :published true}))
       (models/update {:slug slug :title title :body body}))       
     (response/redirect (str "/" slug)))))

(defn delete-post [slug]
   (utils/check-auth
    (models/delete slug)
    (response/redirect "/")))