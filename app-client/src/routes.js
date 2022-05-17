import {
  ADMIN_ROUTE,
  FAVORITE_FILMS,
  FILM_INFO,
  LOGIN_ROUTE,
  MAIN_ROUTE,
  REGISTRATION_ROUTE
} from "./utils/consts";
import AdminPage from "./pages/AdminPage";
import FilmInfoPage from "./pages/FilmInfoPage";
import FavouriteFilmsPage from "./pages/FavouriteFilmsPage";
import HomePage from "./pages/HomePage";
import AuthPage from "./pages/AuthPage";

export const authRoutes = [
  {
    path: ADMIN_ROUTE,
    Component: AdminPage
  },
  {
    path: FAVORITE_FILMS,
    Component: FavouriteFilmsPage
  }
]

export const publicRoutes = [
  {
    path: MAIN_ROUTE,
    Component: HomePage
  },
  {
    path: LOGIN_ROUTE,
    Component: AuthPage
  },
  {
    path: REGISTRATION_ROUTE,
    Component: AuthPage
  },
  {
    path: FILM_INFO + '/:id',
    Component: FilmInfoPage
  }
]
