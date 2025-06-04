const slider = document.querySelector('.slider1');

		function activate(e) {
			const items = document.querySelectorAll('.item1');
			e.target.matches('.next') && slider.append(items[0])
			e.target.matches('.prev')
					&& slider.prepend(items[items.length - 1]);
		}
		document.addEventListener('click', activate,false);
		setInterval(() => {
        const items = document.querySelectorAll('.item1');
        slider.append(items[0]);
    }, 4000);