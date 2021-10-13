import { Link } from 'react-router-dom';
import { FormComponent } from '../../../types';

interface ComponentListItemInterface {
  component: FormComponent;
}
export default function ComponentListItem({ component }: ComponentListItemInterface) {
  return (
    <li>
      <Link to={`/component/${component.key}`}>
        <div className="key">{component.key}</div>
        <div className="description">{component.description}</div>
      </Link>
    </li>
  );
}
