module Exercice2 exposing (..)
import Browser
import Html.Events as Events
import Html exposing (Html, Attribute, div, input, text, button, h1, li, ul)
import Html.Attributes exposing (..)
import Html.Events exposing (onInput, onClick)

helloWorld : String
helloWorld = "Hello World!"

main =
  Browser.sandbox { init = init, update = update, view = view }


sampleInputs = [{prenom = "Théo", nom = "Nardin", numero = "0601020304" }]


-- MODEL
type alias Contact =
  {
  nom : String,
  prenom : String,
  numero : String
  }

type alias Model =
    {contacts :
      {
        contactList : List Contact
      },
    newContact : Contact,
    searchValue : String,
    resList : List Contact
    }

init : Model
init =
  {
    contacts =
      {
      contactList = []
    },
    newContact = Contact "" "" "",
    searchValue = "",
    resList = []
  }

-- UPDATE
type Msg
    = NewPrenom String
    | NewNom String
    | NewNumero String
    | CreateNewContact
    | EditContact
    | NewSearchValue String
    | SearchByName
    | SearchByFirstName
    | SearchByTelNumber


update : Msg -> Model -> Model
update msg model =
  case msg of
    NewPrenom np ->
      { model | newContact = Contact model.newContact.nom np model.newContact.numero}

    NewNom np ->
      { model | newContact = Contact np model.newContact.prenom model.newContact.numero}

    NewNumero np ->
      { model | newContact = Contact model.newContact.nom model.newContact.prenom np}

    CreateNewContact ->
      {
        contacts =
          {
          contactList = (List.append model.contacts.contactList ((Contact model.newContact.nom model.newContact.prenom model.newContact.numero) :: []))
        },
        newContact = Contact "" "" "",
        searchValue = model.searchValue,
        resList = model.resList
      }

    EditContact ->
      model

    NewSearchValue np ->
      {model | searchValue = np}

    SearchByName ->
      {model | resList = List.filter (\a -> a.nom == model.searchValue) model.contacts.contactList}
    SearchByFirstName ->
      {model | resList = List.filter (\a -> a.prenom == model.searchValue) model.contacts.contactList}
    SearchByTelNumber ->
      {model | resList = List.filter (\a -> a.numero == model.searchValue) model.contacts.contactList}


-- VIEW
renderList : List Contact -> Html Msg
renderList lst =
    ul []
        (List.map (\l -> li [] [
        text l,
        button [onClick EditContact] [ text "Editer" ]
         ] ) (convertContactToText lst []))


convertContactToText : List Contact -> List String -> List String
convertContactToText todolst newlst =
  case todolst of
    [] -> newlst
    h :: t -> convertContactToText t (List.append newlst ((h.nom ++ " " ++h.prenom++ " - "++h.numero) :: []))

view : Model -> Html Msg
view model =
  div []
    [
    div [] [
      text ("Contacts "),
      renderList model.contacts.contactList
      ],
    div [] [
      text ("Nouveau contact : "),
      input [ placeholder "Prénom", value model.newContact.prenom, onInput NewPrenom] [],
      input [ placeholder "Nom", value model.newContact.nom, onInput NewNom] [],
      input [ placeholder "Numéro", value model.newContact.numero, onInput NewNumero] [],
      button [ onClick CreateNewContact ] [ text "Créer" ]
      ],
    div [] [
      text("Rechercher :"),
      input [ placeholder "Valeur a rechercher", value model.searchValue, onInput NewSearchValue] [],
      button [ onClick SearchByName ] [ text "Rechercher par nom" ],
      button [ onClick SearchByFirstName ] [ text "Rechercher par prenom" ],
      button [ onClick SearchByTelNumber ] [ text "Rechercher par numero" ]
      ],
    div [] [
      text ("Résultats "),
      renderList model.resList
      ]
    ]
