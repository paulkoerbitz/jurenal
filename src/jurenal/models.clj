(ns jurenal.models 
  (:require [appengine-magic.services.datastore :as ds])
  (:import (play.templates JavaExtensions)
           (java.util Date)))

(ds/defentity Post [^:key slug, title, body, short
                    ^Boolean published, ^Date created-on, ^Date last-changed])

(defn take-words
  "Takes string, returns first n words as a string"
  [n s]
  (apply str (take n (re-seq #"\W*\w+" s))))

(defn create [{title :title body :body short :short published :published}]
  (let [created-on (Date.)
        slug (JavaExtensions/slugify title)
        short (if short short (take-words 50 body))]
    (ds/save! (Post. slug title body short published created-on created-on))))

(defn fetch [slug]
  (let [post (first (ds/query :kind Post :filter (= :slug slug)))]
    (println post)
    post))

(defn fetch-all []
  (ds/query :kind Post))

(defn update [{slug :slug title :title body :body}]
  (let [post (fetch slug)
        now (Date.)]
    (ds/save! (assoc post :title title :body body :last-changed now))))

(defn delete [slug]
  (ds/delete! (ds/query :kind Post :filter (= :slug slug))))
