import { useHistory, useParams } from 'react-router-dom';
import { useApi } from '../../../hooks/use-api';
import { FormComponent } from '../../../types';
import ComponentDetail from '../../../components/route-components/components/component-detail';
import apiRequest, { HTTPMethod } from '../../../services/api-service';
import { Heading } from '@chakra-ui/react';

export default function ComponentDetailRoute() {
  const { key } = useParams<{ key: string }>();
  const history = useHistory();
  const url = `http://localhost:8080/api/component/${key}`;
  const { data, isLoading, error } = useApi<FormComponent>(url);

  async function saveAction(component: Partial<FormComponent>) {
    await apiRequest(url, HTTPMethod.POST, {}, component);
    history.push('/');
  }

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!data) return <p>Error: No data</p>;

  return (
    <>
      <Heading>Edit component</Heading>
      <ComponentDetail component={data} save={saveAction} />
    </>
  );
}
