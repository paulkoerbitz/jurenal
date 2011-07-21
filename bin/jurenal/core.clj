(ns jurenal.core
  (:use 
    compojure.core
    jurenal.views
    [appengine-magic.multipart-params :only [wrap-multipart-params]])
  (:require 
    [appengine-magic.core :as ae]
    [appengine-magic.services.datastore :as ds]))



(defroutes jurenal-app-handler
  (GET "/" [] (index))
  (GET "/create" [] (create-post)) 
  (POST "/update" _ (wrap-multipart-params update-post))
  (GET ["/:slug" :slug #"[-\w]+"] [slug] (show-post slug))
  (GET ["/edit/:slug" :slug #"[-\w-]+"] [slug] (edit-post slug))
  (GET ["/delete/:slug" :slug #"[-\w]+"] [slug] (delete-post slug))
           
;  ;; HTML upload form
;  (GET "/upload" _
;       {:status 200
;        :headers {"Content-Type" "text/html"}
;        :body (str "<html><body>"
;                   "<form action=\"/done\" "
;                   "method=\"post\" enctype=\"multipart/form-data\">"
;                   "<input type=\"file\" name=\"file-upload\">"
;                   "<input type=\"submit\" value=\"Submit\">"
;                   "</form>"
;                   "</body></html>")})
;  ;; handles the uploaded data
;  (POST "/done" _
;        (wrap-multipart-params
;         (fn [req]
;           (let [img (get (:params req) "file-upload")
;                 img-entity (Image. (:filename img)
;                                    (:content-type img)
;                                    (ds/as-blob (:bytes img)))]
;             (ds/save! img-entity)
;             {:status 200
;              :headers {"Content-Type" "text/plain"}
;              :body (with-out-str
;                      (println (:params req)))}))))
;  ;; hit this route to retrieve an uploaded file
;  (GET ["/img/:name", :name #".*"] [name]
;       (let [img (ds/retrieve Image name)]
;         (if (nil? img)
;             {:status 404}
;             {:status 200
;              :headers {"Content-Type" (:content-type img)}
;              :body (.getBytes (:data img))})))
  (ANY "*" _ {:status 404 
              :headers {"Content-Type" "text/html"} 
              :body "<h1>Four-oh-four</h1>"}))

(ae/def-appengine-app jurenal-app #'jurenal-app-handler)
(ae/serve jurenal-app)
