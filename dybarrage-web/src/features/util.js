export const getRoomIdFromUrl = () => {
  const search = window.location.search;

  if(search === '') {
    return null;
  }

  const regx = /roomid=(\d+)/;
  const arr = search.match(regx);

  if(arr === null) {
    return null;
  }

  return arr[1];
}