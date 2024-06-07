import { createReducer } from "@reduxjs/toolkit";


export const getPostOfFollowing = createReducer({},(builder)=>{
    builder
    .addCase('createPostRequest',(state,action)=>{
   
    })
    .addCase('createPostSuccess',(state,action)=>{
        state.posts=action.payload
    })
    .addCase('createPostFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('getFollowingPostRequest',(state,action)=>{
   
    })
    .addCase('getFollowingPostSuccess',(state,action)=>{
        state.posts=action.payload
    })
    .addCase('getFollowingPostFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('postRequest',(state,action)=>{
   
    })
    .addCase('postSuccess',(state,action)=>{
        state.posts=action.payload
    })
    .addCase('postFailure',(state,action)=>{
        state.error=action.payload
    })
})

export const likeUnlikePostReducer = createReducer({},(builder)=>{
    builder
    .addCase('likePostRequest',(state,action)=>{

    })
    .addCase('likePostSuccess',(state,action)=>{
        state.message=action.payload
    })
    .addCase('likePostFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('unlikePostRequest',(state,action)=>{

    })
    .addCase('unlikePostSuccess',(state,action)=>{
        state.message=action.payload
    })
    .addCase('unlikePostFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('addCommentRequest',(state,action)=>{

    })
    .addCase('addCommentSuccess',(state,action)=>{
        state.message=action.payload
    })
    .addCase('addCommentFailure',(state,action)=>{
        state.error=action.payload
    })
    .addCase('deleteCommentRequest',(state,action)=>{

    })
    .addCase('deleteCommentSuccess',(state,action)=>{
        state.message=action.payload
    })
    .addCase('deleteCommentFailure',(state,action)=>{
        state.error=action.payload
    })
})


export const accountReducer = createReducer({},(builder)=>{
     builder
     .addCase('accountInfoRequest',(state,action)=>{

     })
     .addCase('accountInfoSuccess',(state,action)=>{
        state.info=action.payload
     })
     .addCase('accountInfoFailure',(state,action)=>{
       state.error=action.payload
     })
})


export const storyReducer = createReducer({},(builder)=>{
    builder
      .addCase('createStoryRequest',(state,action)=>{

      })
      .addCase('createStorySuccess',(state,action)=>{
        state.story= action.payload
      })
      .addCase('createStoryFailure',(state,action)=>{
        state.error=action.payload
      })
      .addCase('getFollowingStoryRequest',(state,action)=>{

      })
      .addCase('getFollowingStorySuccess',(state,action)=>{
        state.stories= action.payload
      })
      .addCase('getFollowingStoryFailure',(state,action)=>{
        state.error=action.payload
      })
})