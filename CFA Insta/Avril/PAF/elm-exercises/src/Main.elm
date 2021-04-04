module Main exposing (..)
import String
import Html
import Maybe

helloWorld = 0
alphabet = ['a','b','c', 'd', 'e', 'f', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

main : Html.Html msg
main =
  Html.text (Debug.toString (fibo 10))


evenWord word =
  if (modBy 2 (String.length word)) == 0 then True else False


palindrome word =
  (String.toList word) == (List.reverse (String.toList word))


sum1tonNoRec n =
  List.sum (List.range 0 n)


sum1tonRec n =
  if n /= 0 then
    n + (sum1tonRec (n - 1))
  else
    n


facRec n =
  if n /= 0 then
    n * (facRec (n - 1))
  else
    1


fibo n =
  if n > 1 then
    fibo (n - 1) + fibo (n - 2)
  else
    1
