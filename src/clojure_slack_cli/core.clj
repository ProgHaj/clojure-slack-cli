(ns clojure-slack-cli.core
  (:require ;[clj-http.client :as client]
            [clojure.data.json :as json]
            [gniazdo.core :as ws]
            [org.httpkit.client :as http]))
(use 'clojure.string)

(def token (clojure.string/replace (slurp "resources/token.txt") "\n" ""))



(def wss (clojure.string/replace ((split((split (slurp (str "https://slack.com/api/rtm.start?" token)) #"url") 2) #"[\"]") 2) "\\" ""))

(defn handle-message [msg]
  (do
    (let [json (json/read-str msg)
          text (json "text")
          user (json "user")
          m_type (json "type")
          channel (json "channel")]
      (if (= m_type "message")
        (prn (str "[" channel "] " user ": " text))))))

(defn handle-message-debug [msg]
  (prn (json/read-str msg)))

(def socket (ws/connect
                wss
                :on-receive #(handle-message %)))
;

(defn send [msg channel]
    (ws/send-msg socket (json/write-str {
                         :id 1
                         :type "message"
                         :channel channel
                         :text msg
                        } )))

(defn close-socket []
      (ws/close socket))
  
(defn connection []
  (loop [channel "C0DDXU6RL"]
    (let [msg (read-line)]
      (cond
        (= ((split msg #" ") 0) "!channel") (recur ((split msg #" ") 1))
        (= ((split msg #" ") 0) "!exit") (close-socket)
        :else (do (send msg)
                  (recur channel))))))




