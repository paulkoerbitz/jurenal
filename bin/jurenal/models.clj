(ns jurenal.models 
  (:require [appengine-magic.services.datastore :as ds]
            [jurenal.utils :as utils])
  (:import (java.util Date)
           (com.google.appengine.api.datastore Text)))

(ds/defentity Post [^:key slug, title, ^Text body, short,
                    ^Boolean published, ^Date created-on, ^Date last-changed])

(defn take-words
  "Takes string, returns first n words as a string"
  [n s]
  (apply str (take n (re-seq #"\W*\w+" s))))

(defn elide-after [n s]
  (let [elided (take-words n s)]
    (if (= elided s)
      elided
      (str elided " ..."))))

(defn create [{title :title body :body short :short published :published}]
  (let [created-on (Date.)
        slug (utils/slugify title)
        short (if short short (elide-after 50 body))]
    (ds/save! (Post. slug title (Text. body) short published created-on created-on))))

(defn fetch [slug]
  (let [post (first (ds/query :kind Post :filter (= :slug slug)))]
    post))

(defn fetch-all []
  (ds/query :kind Post :sort [[:last-changed :dsc]]))

(defn update [{slug :slug title :title body :body}]
  (let [post (fetch slug)
        now (Date.)]
    (ds/save! (assoc post :title title :body (Text. body) :last-changed now))))

(defn delete [slug]
  (ds/delete! (ds/query :kind Post :filter (= :slug slug))))
