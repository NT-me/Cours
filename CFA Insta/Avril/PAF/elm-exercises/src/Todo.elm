module Todo exposing (..)
import Browser
import Html exposing (Html, Attribute, div, input, text, button, h1, li, ul)
import Html.Attributes exposing (..)
import Html.Events exposing (onInput, onClick)
import ParseInt exposing (..)
-- MAIN


main =
  Browser.sandbox { init = init, update = update, view = view }

  -- MODEL


type TaskStatus
  = InProgress
  | Over
  | ToCome

type alias Todo =
  {
  title : String,
  content : String,
  state : TaskStatus
  }

inputs = [{title = "task 1", content = "Envahir la Pologne", state = Over}]

type alias Model =
    {todos :
      {
        inProgress : List Todo,
        over : List Todo,
        toCome : List Todo
      }
    }

init : Model
init =
  {
    todos =
      {
      inProgress = List.filter (\i -> i ) inputs,
      over = [],
      toCome = []
    }
  }



-- UPDATE

type Msg = Int


update : Msg -> Model -> Model
update msg model =
  case msg of
    _ -> model


convertTodoToText : List Todo -> List String -> List String
convertTodoToText todolst newlst =
  case todolst of
    [] -> newlst
    h :: t -> convertTodoToText t (List.append newlst ((h.title ++ " - " ++h.content) :: []))



renderList : List Todo -> Html msg
renderList lst =
    ul []
        (List.map (\l -> li [] [ text l ]) (convertTodoToText lst []))

-- VIEW


view : Model -> Html Msg
view model =
  div []
    [
    div [] [
      text ("Over "),
      renderList model.todos.over
      ],
    div [] [
      text ("inProgress "),
      renderList model.todos.inProgress
      ],
    div [] [
      text ("ToCome "),
      renderList model.todos.toCome
      ]
    -- renderList model.todos
    ]
