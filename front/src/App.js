import logo from './logo.svg';
import './App.css';

function App() {
  const [msg, setMsg] = useState([]);
  useEffect(() => {
    fetch("/users")
        .then((res) => {return res.json();})
        .then((data) => {setMsg(data);})
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

        <ul>
          {msg.map((content, idx) => <li key={`${idx} - ${content}`}>{content}</li>)}
        </ul>


      </header>
    </div>
  );
}

export default App;
