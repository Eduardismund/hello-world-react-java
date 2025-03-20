/**
 *
 * @returns {Promise<string>}
 */
export function fetchHello() {
    return fetch('/api/hello')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })

}