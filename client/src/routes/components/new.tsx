import { useHistory, useParams } from 'react-router-dom';
import { ComponentType } from '../../types';
import ComponentDetail from '../../components/route-components/components/component-detail';
import apiRequest, { HTTPMethod } from '../../services/api-service';

export default function NewComponentDetailRoute() {
  const history = useHistory();
  const url = `http://localhost:8080/api/component`;

  async function saveAction(component: Partial<ComponentType>) {
    if (!component.key || !component.code || !component.key.length || !component.code.length) {
      alert('Key or code is blank.');
      return;
    }
    await apiRequest(url, HTTPMethod.POST, {}, component);
    history.push('/');
  }

  return <ComponentDetail component={{}} save={saveAction} />;
}
