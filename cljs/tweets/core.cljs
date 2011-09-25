(ns tweets.core
  (:require [goog.dom :as dom]
            [goog.net.Jsonp :as jsonp]))

(def twitter-uri "https://api.twitter.com/1/statuses/user_timeline.json")

(defn retrieve [payload callback]
  (.send (goog.net.Jsonp. twitter-uri) payload callback))

(defn insert-tweet [text]
  (dom/insertChildAt
   (dom/getElement "tweets")
   (dom/createDom "div" "tweet"
                  (dom/createDom "p" "" text)
                  (dom/createDom "hr"))))

(defn insert-tweets [json]
  (let [tweets (js->clj json :keywordize-keys true)]
    (doseq [tweet tweets]
      (let [text (:text tweet)]
        (insert-tweet text)))))

(defn ^{:export insert_tweets} get-tweets []
  (retrieve (.strobj {"screen_name" "paulkoer" "count" "5"}) insert-tweets))

