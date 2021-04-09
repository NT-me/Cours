module Simple exposing (..)
import Browser
import Html exposing (Html, Attribute, div, input, text, button)
import Html.Attributes exposing (..)
import Html.Events exposing (onInput, onClick)
import ParseInt exposing (..)
-- MAIN


main =
  Browser.sandbox { init = init, update = update, view = view }



-- MODEL

type alias Model = {value: Int, content: String, nb1: Int, nb2: Int, opRes: Int}

init : Model
init =
  {content = "", value = 0, nb1 = 0, nb2 = 0, opRes = 0}


-- UPDATE

type Msg = Increment | Decrement | BiIncrement | BiDecrement | RAZ | Salute String | Sub | Add | NewValue1 String | NewValue2 String

isInt n = case (ParseInt.parseInt n) of
  Err _ -> 0
  Ok b -> b

update : Msg -> Model -> Model
update msg model =
  case msg of
    Increment ->
      { model | value = model.value + 1 }

    Decrement ->
      { model | value = model.value - 1 }

    BiIncrement ->
      { model | value = model.value + 2 }

    BiDecrement ->
      { model | value = model.value - 2 }

    RAZ ->
      { model | value = 0 }

    Salute newContent ->
      { model | content = newContent }

    Sub ->
      { model | opRes = (model.nb1 - model.nb2) }

    Add ->
      { model | opRes = (model.nb1 + model.nb2) }

    NewValue1 nv1 ->
      { model | nb1 = isInt nv1}

    NewValue2 nv2 ->
      { model | nb2 = isInt nv2}

-- VIEW


view : Model -> Html Msg
view model =
  div []
    [
    button [ onClick Decrement ] [ text "- 1" ],
    button [ onClick BiDecrement ] [ text "- 2" ],
    div []
      [ text (String.fromInt model.value) ],
      button [ onClick Increment ] [ text "+ 1" ],
      button [ onClick BiIncrement ] [ text "+ 2" ],
      div []
        [
          button [ onClick RAZ ] [ text "RaZ" ]
        ],
      input [ placeholder "Quel est votre nom", value model.content, onInput Salute ] [],
      div [] [ text ("Hello " ++ model.content)  ],

      input [ placeholder "nb1", value (String.fromInt model.nb1), onInput NewValue1] [],
        button [ onClick Add ] [ text "+" ],
        button [ onClick Sub ] [ text "-" ],
      input [ placeholder "nb2", value (String.fromInt model.nb2), onInput NewValue2] [],
      div [] [ text (String.fromInt model.opRes) ]
    ]
