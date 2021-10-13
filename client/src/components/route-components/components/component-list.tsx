import { FormComponent } from '../../../types';
import ComponentListItem from './component-list-item';

interface componentListInterface {
  components: Array<FormComponent>;
}

export default function componentList({ components }: componentListInterface) {
  return (
    <>
      <h1>Component List</h1>
      <ul id="field-components">
        {components.map((component) => (
          <ComponentListItem key={component.id} component={component} />
        ))}
      </ul>
    </>
  );
}
