module Main exposing (..)
import String
import Html
import Maybe

helloWorld = 0
alphabet = ['a','b','c', 'd', 'e', 'f', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
listInt = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

main : Html.Html msg
main =
  Html.text (Debug.toString (conc listInt listInt))
  -- Html.text (Debug.toString (sumListIte listInt))
  -- Html.text (Debug.toString (sumListRec listInt 0))
  -- Html.text (Debug.toString (allFilters listInt [] (\n -> (modBy 2 n) == 1)))
  -- Html.text (Debug.toString (evenFilter listInt []))
  -- Html.text (Debug.toString (long ['a', 'b'] 0))
  -- Html.text (Debug.toString (fibo 10))


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


get_list_chars : List Char -> List Char -> List Char
get_list_chars str_list list =
        case str_list of
            [] -> list
            h :: t -> if ((Char.toCode h) >= 65 && (Char.toCode h) <= 90 && (not (List.member h list))) then get_list_chars t (h::list) else get_list_chars t list


pangramme : String -> Bool
pangramme str =
    if List.length (get_list_chars (String.toList (String.toUpper str)) []) == 26 then True else False


long li index =
  case li of
    [] -> index
    h :: t -> long t (index + 1)


incl: List Int -> Int -> Bool
incl li x =
  case li of
    [] -> False
    h :: t ->
      if (h == x) then
        True
      else
        incl t x


evenFilter: List Int -> List Int -> List Int
evenFilter li evli =
  case li of
    [] -> evli
    h :: t ->
      if ((modBy 2 h) == 0) then
        evenFilter t (List.append evli (h :: []))
      else
        evenFilter t evli


allFilters: List a -> List a -> (a -> Bool) -> List a
allFilters li retli expr =
  case li of
    [] -> retli
    h :: t -> if expr h then
      allFilters t (List.append retli (h :: [])) expr
      else
        allFilters t retli expr


sumListRec: List Int -> Int -> Int
sumListRec li value =
  case li of
    [] -> value
    h :: t ->
      sumListRec t (value + h)


sumListIte: List Int -> Int
sumListIte li =
  List.foldl (\x a -> x + a) 0 li


conc li1 li2 =
  case li2 of
    [] -> li1
    h :: t ->
      conc (List.append li1 (h :: [])) t
