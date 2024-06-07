import { configureStore } from "@reduxjs/toolkit";
import { allUserReducer, profileReducer, userReducer, usersReducer } from "./Reducers/User";
import { accountReducer, getPostOfFollowing, likeUnlikePostReducer, storyReducer } from "./Reducers/Post";





export const store = configureStore({
    reducer:{
        user:userReducer,
        users:usersReducer,
        alluser:allUserReducer,
        post:getPostOfFollowing,
        account:accountReducer,
        profile:profileReducer,
        likeUnlike:likeUnlikePostReducer,
        story:storyReducer
    }
})