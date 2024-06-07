import { Button } from '@mui/material'
import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import { createStory } from '../Action/Post'
import { useNavigate } from 'react-router-dom'

function Story() {
    const [image,setImage] = useState(null)
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const submitHandler = async(e) =>{
        e.preventDefault()
        await dispatch(createStory(image))
        navigate("/home")
    }
  return (
    <div className="storyContainer">
    <div className='story'>
            <img width={"80px"}  src={image && URL.createObjectURL(image)} alt=''/>
        <form onSubmit={submitHandler}>
            <label>Image </label>
            <input type='file' accept='image/*' onChange={(e)=> setImage(e.target.files[0])}/>
            <Button variant="contained" type='submit' >submit</Button>
        </form>
    </div>
    </div>
  )
}

export default Story