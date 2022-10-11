function Movies (){
    
    const MOVIE_DATA = [
        { id: 1, title: 'Toy Story', releaseYear: 1995 },
        { id: 2, title: 'The Iron Giant', releaseYear: 1999 },
        { id: 3, title: 'Spider-Man: Into the Spider-Verse', releaseYear: 2018 },
      ];
    
    return(
        <>
            <h2>Movies</h2>
            {MOVIE_DATA.map(m => 
            <h3>{m.title}</h3>
            )}
            {
                MOVIE_DATA.map(m => 
            <p>{m.releaseYear}</p>
            )
            }
        </>
    );
}


export default Movies;