function Movie({movie, deleteMovie}) {

    const handleDeleteMovie = () => {
        deleteMovie(movie.id);
    }


    return(
        <div>
            <h3>{movie.title}</h3>
            <p>{movie.releaseYear}</p>
            <button onClick={handleDeleteMovie}>Delete</button>
        </div>
    );
}

export default Movie;