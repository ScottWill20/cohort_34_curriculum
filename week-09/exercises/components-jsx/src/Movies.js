import { useState } from "react";
import Movie from "./Movie";

function Movies (){
    
    const MOVIE_DATA = [
        { id: 1, title: 'Toy Story', releaseYear: 1995 },
        { id: 2, title: 'The Iron Giant', releaseYear: 1999 },
        { id: 3, title: 'Spider-Man: Into the Spider-Verse', releaseYear: 2018 },
      ];

    const [movies, setMovies] = useState(MOVIE_DATA);

    // Write a function to add a movie
    const handleAddMovieClick = () => {
        const nextId = movies.length > 0 ? Math.max(...movies.map(m => m.id)) + 1 : 1;

        // create a new movie object with a unique id
        const newMovie = {
            id: nextId,
            title: 'Everything, Everywhere, All At Once',
            releaseYear: 2021
        }

        const newMovies = [...movies, newMovie];
        // updating the movies state
        setMovies(newMovies);
    }

    const deleteMovie = (movieId) => {

        setMovies(movies.filter( m => m.id != movieId));
    }

    return(
        <>
            <h2>Movies</h2>
            <button onClick= {handleAddMovieClick}>Add Movie</button>
            {movies.map(movie => (
                <Movie key = {movie.id} movie = {movie} deleteMovie = {deleteMovie}/>
            ))}
        </>
    );
}


export default Movies;