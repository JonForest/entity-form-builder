export enum HTTPMethod {
  'GET' = 'GET',
  'POST' = 'POST',
}
export default async function apiRequest(
  url: string,
  verb: HTTPMethod = HTTPMethod.GET,
  headers: { [header: string]: string } = {},
  dataToSend?: object
) {
  // Fetch the data
  const init: { [key: string]: any } = {
    method: verb,
    headers,
  };
  if (dataToSend) {
    init['headers']['Content-Type'] = 'application/json';
    init['body'] = JSON.stringify(dataToSend);
  }
  const response = await fetch(url, init);
  const data = await response.json();
  return data;
}
