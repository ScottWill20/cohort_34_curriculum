import Heading from './Heading';
import Numbers from './Numbers';
import Movies from './Movies';

function App() {
  return (
    <>
      <h2>Ramping Up On React</h2>
      <Heading text="This is the first heading"/>
      <Heading text="This is the second heading"/>
      <Heading text="This is the third heading"/>
      <Heading text="This is the fourth heading"/>
      <Numbers/>
      <Movies/>
    </>
  );
}

export default App;
