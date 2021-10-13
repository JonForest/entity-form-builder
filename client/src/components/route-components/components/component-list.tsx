import { FormComponent } from '../../../types';
import ComponentListItem from './component-list-item';

interface componentListInterface {
  components: Array<FormComponent>;
}

export default function componentList({ components }: componentListInterface) {
  return (
    <>
      <ul id="field-components">
        {components.map((component) => (
          <ComponentListItem key={component.id} component={component} />
        ))}
      </ul>
    </>
  );
}
