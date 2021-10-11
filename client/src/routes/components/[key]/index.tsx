import { useHistory, useParams } from 'react-router-dom';
import { useApi } from '../../../hooks/use-api';
import { ComponentType } from '../../../types';
import ComponentDetail from '../../../components/route-components/components/component-detail';
import apiRequest, { HTTPMethod } from '../../../services/api-service';

export default function ComponentDetailRoute() {
  const { key } = useParams<{ key: string }>();
  const history = useHistory();
  const url = `http://localhost:8080/api/component/${key}`;
  const { data, isLoading, error } = useApi<ComponentType>(url);

  async function saveAction(component: Partial<ComponentType>) {
    await apiRequest(url, HTTPMethod.POST, {}, component);
    history.push('/');
  }

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!data) return <p>Error: No data</p>;

  return <ComponentDetail component={data} save={saveAction} />;
}
