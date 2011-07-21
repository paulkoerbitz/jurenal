(ns jurenal.models 
  (:require [appengine-magic.services.datastore :as ds])
  (:use blog.system.utils))

(ds/defentity Post [^:key ^int id, title, body])

(defn create [{title :title body :body}]
  (if-let [id (:id (first (ds/query :kind Post :sort [[:id :dsc]])))]
    (ds/save! (Post. (inc id) title body))
    (ds/save! (Post. 1 title body))))

(defn fetch [^Integer id]
  (map->soy (first (ds/query :kind Post :filter (= :id (. Integer parseInt id))))))

(defn fetch-all []
  (ds/query :kind Post :sort [[:id :dsc]]))

(defn update [{id :id title :title body :body}]
  (println id)
  (println title)
  (println body)
  (ds/save! (assoc (first (ds/query :kind Post :filter (= :id (. Integer parseInt id)))) 
              :title title :body body)))

(defn delete [id]
  (ds/delete! (ds/query :kind Post :filter (= :id id))))
