import { Link } from 'react-router-dom';
import { ComponentType } from '../../../types';

interface componentListInterface {
  components: Array<ComponentType>;
}

export default function componentList({ components }: componentListInterface) {
  return (
    <>
      <h1>Component List</h1>
      <ul>
        {components.map((component) => (
          <li key={component.key}>
            <Link to={`/component/${component.key}`}>{component.key}</Link>
          </li>
        ))}
      </ul>
    </>
  );
}
