import { createReducer } from "@reduxjs/toolkit";



export const userReducer = createReducer({isAuthenticated:false},(builder)=>{
    builder
    .addCase('loginRequest',(state,action)=>{

    })
    .addCase('loginSuccess',(state,action)=>{
        state.isAuthenticated=true;
    })
    .addCase('loginFailure',(state,action)=>{

    })
    .addCase('logoutRequest',(state,action)=>{

    })
    .addCase('logoutSuccess',(state,action)=>{
        state.isAuthenticated=false;
    })
    .addCase('logoutFailure',(state,action)=>{

    })
})


export const usersReducer = createReducer({},(builder)=>{
    builder.addCase('searchRequest',(state,action)=>{

    })
    .addCase('searchSuccess',(state,action)=>{
        state.users=action.payload;
    })
    .addCase('searchFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('followRequest',(state,action)=>{

    })
    .addCase('followSuccess',(state,action)=>{
        state.message=action.payload;
    })
    .addCase('followFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('unfollowRequest',(state,action)=>{

    })
    .addCase('unfollowSuccess',(state,action)=>{
        state.message=action.payload;
    })
    .addCase('unfollowFailure',(state,action)=>{
        state.error=action.payload
    })
})

export const allUserReducer = createReducer({alluser:[]},(builder)=>{
    builder.addCase('allUserRequest',(state,action)=>{

    })
    .addCase('allUserSuccess',(state,action)=>{
        state.users=action.payload
    })
    .addCase('allUserFailure',(state,action)=>{
      state.error=action.payload  
    })
})

export const profileReducer = createReducer({},(builder)=>{
    builder
    .addCase('userProfileRequest',(state,action)=>{

    })
    .addCase('userProfileSuccess',(state,action)=>{
      state.info=action.payload
    })
    .addCase('userProfileFailure',(state,action)=>{
        state.error=action.payload
    })
})