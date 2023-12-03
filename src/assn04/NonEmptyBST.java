package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T new_element){
		if (new_element.compareTo(_element) < 0) {
			if (_left.getHeight() == -1) {
				_left = new NonEmptyBST(new_element);
			}
			else {
				_left.insert(new_element);
			}
		}
		else {
			if (_right.getHeight() == -1) {
				_right = new NonEmptyBST(new_element);
			} else {
				_right.insert(new_element);
			}
		}
		return this;
	}

	// TODO: findMin
	@Override
	public T findMin() {
		if (_left.getHeight() != -1) {
			return _left.findMin();
		}
		else {
			return _element;
		}
	}

	// TODO: remove
	@Override
	public BST<T> remove(T new_element) {
		// if the tree is empty, nothing to remove
		if (_element == null) {
			return this;
		}

		int comparison = new_element.compareTo(_element);

		if (comparison < 0) {
			// element to remove is in the left subtree
			_left = _left.remove(new_element);
		}

		else if (comparison > 0) {
			// element to remove is in the right subtree
			_right = _right.remove(new_element);
		}

		else {
			// if current element is the one to be removed

			if (_left.getHeight() == -1 && _right.getHeight() == -1) {
				// when an element has no children
				return new EmptyBST<T>();
			}

			if (_left instanceof EmptyBST) {
				return _right;
			}

			else if (_right instanceof EmptyBST) {
				return _left;
			}

			else {
				// when an element has both children
				T replacement_value = _right.findMin();
				_right = _right.remove(replacement_value);
				_element = replacement_value;
			}
		}
		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element);
		System.out.print(" ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element);
		System.out.print(" ");
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
