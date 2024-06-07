import axios from "axios"

export const createPost = (description,location,image) => async(dispatch) =>{
   try {
    dispatch({
      type:"createPostRequest"
    })
    const {data} = await axios.post("/api/post",
      {description,location,image},{
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })

     dispatch({
        type:"createPostSuccess",
        payload:data
     })

   } catch (error) {
     dispatch({
       type:"createPostFailure",
       payload:error
     })
   }
}

export const getFollowingPost = () => async(dispatch) =>{
  try {
     dispatch({
       type:"getFollowingPostRequest"
     })
  
         const {data} = await axios.get("/api/post/all-follow-post")
     
     dispatch({
       type:"getFollowingPostSuccess",
       payload:data
     })
  } catch (error) {
    dispatch({
        type:"getFollowingPostFailure"
    })
  }
}

export const getMyPost = () => async(dispatch) =>{
   try {
    dispatch({
      type:"postRequest"
    })
 
   const {data} = await axios.get("/api/post/user")
   
   dispatch({
      type:"postSuccess",
      payload:data
   })
   } catch (error) {
     dispatch({
       type:"postFailure",
       payload:error
     })
   }
}

export const likePost = (postId) => async(dispatch) =>{
   try {
    dispatch({
      type:"likePostRequest"
    })

   const {data} = await axios.get(`/api/like/${postId}`)
     console.log(data);
    dispatch({
       type:"likePostSuccess",
       payload:data
    })
   } catch (error) {
     dispatch({
       type:"likePostFailure",
       payload:error
     })
   }
}

export const unlikePost = (postId) => async(dispatch) =>{
 try {
    dispatch({
      type:"unlikePostRequest"
    })
 
    const {data} = await axios.get(`/api/unlike/${postId}`)
   
    dispatch({
      type:"unlikePostSuccess",
      payload:data
    })
 } catch (error) {
    dispatch({
      type:"unlikePostFailure",
      payload:error
    })
 }
}

export const addComment = (description,postId) => async(dispatch) =>{
 try {
   dispatch({
     type:"addCommentRequest"
   })
 
    const {data} = await axios.post(`/api/comment?postId=${postId}`,{description})
     console.log(data);
    dispatch({
      type:"addCommentSuccess",
       payload:data
    })
 } catch (error) {
   dispatch({
     type:"addCommentFailure",
     payload:error
   })
 }
}

export const deleteComment = (commentId) => async(dispatch) =>{
   try {
     dispatch({
       type:"deleteCommentRequest"
     })

    const {data} = await axios.delete(`/api/comment/${commentId}`)
    console.log(data);
     dispatch({
       type:"deleteCommentSuccess",
       payload:data
     })
   } catch (error) {
     dispatch({
       type:"deleteCommentFailure"
     })
   }
}


export const getAccountInfo = () => async(dispatch) =>{
   try {
    dispatch({
      type:"accountInfoRequest"
    })
 
    const {data:post} = await axios.get("/api/post/post-cnt")

    const {data:following} = await axios.get("/api/user/following-cnt")

    const {data:followers} = await axios.get("/api/user/followers-cnt")
      const data = {
        post,
        following,
        followers
      }
    dispatch({
      type:"accountInfoSuccess",
      payload:data
    })
   } catch (error) {
     dispatch({
       type:"accountInfoFailure"
     })
   }
}

export const createStory = (file) => async(dispatch) => {
   try {
     dispatch({
       type:"createStoryRequest"
     })
 
    const {data} = await axios.post("/api/story",
       {file},
       {
         headers: {
           'Content-Type': 'multipart/form-data'
         }
       }
     )
 
     dispatch({
       type:"createStorySuccess",
       payload:data
     })
   } catch (error) {
     dispatch({
      type:"createStoryFailure",
      payload:error
     })
   }

}

export const getFollowingStory = () => async(dispatch) =>{
   try {
    dispatch({
      type:' '
    })
    const {data:mystory} = await axios.get("/api/story/mystory")

    const {data:followingstory} = await axios.get("/api/story/following-user")
    const data = {
      mystory,
      followingstory
    }
    dispatch({
      type:"getFollowingStorySuccess",
      payload:data
    })
   } catch (error) {
     dispatch({
       type:'getFollowingStoryFailure',
       payload:error
     })
   }
}