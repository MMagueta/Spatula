(ns spatula.core
  (:gen-class)
  (:refer-clojure :exclude [==])
  (:use clojure.core.logic)
  (:require [net.cgrand.enlive-html :as html]))

(def test-url "#")

(defn page-loader [link]
  (html/html-resource (java.net.URL. link)))

(defn fetch-tables [n]
  (take n (-> (page-loader test-url)
              (html/select [:table :> :tbody]) )))

(defn logic-scrap [data]
  (run* [q]
    (fresh [?tag ?content ?pm]
      (featurec ?pm {:tag ?tag :content ?content})
      (membero ?content ["Brazil"])
      (== q [?pm])
      (membero ?pm data))))

(defn -main [& args]
  (->> (flatten (map (fn [x] (:content x)) (:content (first (fetch-tables 1)))))
       (filter (fn [x] (not= "\n" x)))
       (map (fn [x] {:tag (:tag x) :content (first (:content x))}))))
