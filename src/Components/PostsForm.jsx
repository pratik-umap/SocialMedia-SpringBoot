import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { createPost } from '../Action/Post'

function PostsForm() {
  const [description,setDescription]=useState("")
  const [location,setLocation]=useState("")
  const [image,setImage]=useState(null)
 
  const dispatch = useDispatch()
 
  const submitHandler = (e) => {
    e.preventDefault();
    dispatch(createPost(description,location,image))
  }

  return (
    <div className='postForm'>
        <form onSubmit={submitHandler}>
           <img width={"50px"} style={{borderRadius:"50%"}} src={image && URL.createObjectURL(image)} alt=''/>
            <div className="">
              <label for="description">Description : </label>
              <input type='text' value={description} className='description' onChange={(e)=> setDescription(e.target.value)}/>
            </div>
            <div className="">
              <label for="location">location : </label>
              <input type='text' value={location} id='location' onChange={(e)=> setLocation(e.target.value)}/>
            </div>
           <div className="">
              <label for="file">Image : </label>
              <input type='file' id='file' accept='image/*' onChange={(e)=> setImage(e.target.files[0])}/>
           </div>
           
            <button type='submit' className='postBtn'>add post</button>
        </form>
    </div>
  )
}

export default PostsForm