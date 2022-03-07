module Exercice1 exposing (..)

import Html exposing (Html)

helloWorld : String
helloWorld = "Hello World!"

main : Html msg
main = Html.text (Debug.toString helloWorld)


type Node =
  Node
  {
  value : Maybe Char,
  terminal : Bool,
  infNode : Maybe Node,
  eqNode : Maybe Node,
  supNode : Maybe Node
  }


-- type Tree =
--   Tree
--   {
--   root : Node
--   }

empty : Node
empty =
  Node {value = Nothing, terminal = True , infNode = Nothing, eqNode = Nothing, supNode = Nothing}


-- Code qui marche pas :/
-- newNode : Node -> Char -> Node
-- newNode node letter =
--   case node of
--     Nothing -> Node { value = letter, terminal = True , infNode = Nothing, eqNode = Nothing, supNode = Nothing }
--
--
-- insert : String -> Node -> Node
-- insert word tree =
--     case (String.toList word) of
--       [] -> tree
--       h :: t -> 1
