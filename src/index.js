import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Home from './Components/Home';
import PostsForm from './Components/PostsForm';
import SearchUser from './Components/SearchUser';
import UserAccount from './Components/UserAccount';
import Login from './Components/Login';
import { Provider } from 'react-redux';
import {store} from './store'
import Profile from './Components/Profile';
import Story from './Components/Story';


const router = createBrowserRouter([
  {
    path:"/",
    element:<App />,
    children:[
      {
        path:"/home",
        element:<Home />
      },
      {
        path:"/post",
        element:<PostsForm />
      },
      {
        path:"/search",
        element:<SearchUser />
      },
      {
        path:"/account",
        element:<UserAccount/>
      },
      {
        path:"/login",
        element:<Login/>
      },
      {
        path:"/profile/:id",
        element:<Profile />
      },
      {
        path:"/story",
        element:<Story />
      }
    ]
  }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <React.StrictMode>
    <RouterProvider router={router} />
    </React.StrictMode>
  </Provider>
);

