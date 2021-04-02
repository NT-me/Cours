module Main exposing (..)

import Html
import String

helloWorld = 0

main : Html.Html msg
main =
  palindrome "helloWorld"


palindrome word =
  let taille = String.length word in
  if taille modBy 2 == 0 then "pair" else "impair"
