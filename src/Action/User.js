import axios from "axios"
import Cookies from 'js-cookie'

export const login = (username,password) => async (dispatch) =>{
  try {  
     dispatch({
       type:"loginRequest"
     })
     
     const {data}= await axios.post("/auth/login",
                  {username,password},
                  {
                    headers:{"content-type":"application/json"},
                    withCredentials:true
                  });
      dispatch({
        type:"loginSuccess",
      })
      Cookies.set('token',data.token)
      localStorage.setItem('user',JSON.stringify(data))
  } catch (error) {
    dispatch({
      type:"loginFailure",
      error:error.message
    })
  }

}

export const logout = () => async(dispatch) =>{
   try {
    dispatch({
       type:"logoutRequest"
    })

      const {data} = await axios.get("/auth/logout")

      dispatch({
        type:"logoutSuccess",
        payload:data
      })
      Cookies.remove('token')
      localStorage.removeItem('user')
   } catch (error) {
     dispatch({
       type:"logoutFailure",
       payload:error
     })
   } 
}

export const searchUser = (search) => async(dispatch) =>{
   try {
    dispatch({
      type:"searchRequest"
    })

    const { data } = await axios.get(`/api/user/search?search=${search}`)
    // console.log(data);
    dispatch({
      type:"searchSuccess",
      payload:data
    })
   } catch (error) {
     dispatch({
      type:"searchFailure",
      payload:error
     })
   }
} 

export const getAllUser = () => async(dispatch) =>{
   try {
     dispatch({
       type:"allUserRequest"
     })
 
      const {data} = await axios.get("/api/user/all")

      dispatch({
        type:"allUserSuccess",
        payload:data
      })
   } catch (error) {
     dispatch({
       type:"allUserFailure",
       payload:error
     })
   }
     
}

export const getUserProfile = (id) => async(dispatch) =>{
   try {
    dispatch({
      type:"userProfileRequest"
    })

     const {data:posts} = await axios.get(`/api/post/user?id=${id}`)

     const {data:post} = await axios.get(`/api/post/post-cnt?id=${id}`)

     const {data:following} = await axios.get(`/api/user/following-cnt?id=${id}`)

     const {data:followers} = await axios.get(`/api/user/followers-cnt?id=${id}`)

     const {data:followUser} = await axios.get(`/api/follow-user?id=${id}`)

     const data = {
       post,
       posts,
       following,
       followers,
       followUser
     }
    dispatch({
       type:"userProfileSuccess",
       payload:data
    })

   } catch (error) {
     dispatch({
       type:"userProfileFailure",
       payload:error
     })
   }
}

export const followUser = (id) => async(dispatch) =>{
 try {
    dispatch({
      type:"followRequest"
    })
 
    const {data} = await axios.get(`/api/follow/${id}`)
 
    dispatch({
      type:"followSuccess",
      payload:data
    })
 } catch (error) {
    dispatch({
       type:"followFailure",
       payload:error
    })
 }
}

export const unfollow = (id) => async(dispatch) =>{
   try {
     dispatch({
       type:"unfollowRequest"
     })
     const {data}= await axios.get(`/api/unfollow/${id}`)

     dispatch({
       type:"unfollowSuccess",
       payload:data
     })
   } catch (error) {
     dispatch({
       type:"unfollowFailure",
       payload:error
     })
   }
}